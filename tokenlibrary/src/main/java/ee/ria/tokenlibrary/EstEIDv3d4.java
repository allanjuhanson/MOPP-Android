/*
 * Copyright 2017 Riigi Infosüsteemide Amet
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package ee.ria.tokenlibrary;

import android.util.SparseArray;

import java.io.UnsupportedEncodingException;

import ee.ria.scardcomlibrary.SmartCardComChannel;
import ee.ria.tokenlibrary.exception.SecureOperationOverUnsecureChannelException;
import ee.ria.tokenlibrary.exception.SignOperationFailedException;
import ee.ria.tokenlibrary.exception.TokenException;
import ee.ria.tokenlibrary.util.AlgorithmUtils;
import ee.ria.tokenlibrary.util.Util;

public class EstEIDv3d4 extends EstEIDToken {

    public EstEIDv3d4(SmartCardComChannel comChannel) {
        super(comChannel);
    }

    @Override
    public byte[] sign(PinType type, String pin, byte[] data, boolean ellipticCurveCertificate) {
        verifyPin(type, pin.getBytes());
        try {
            selectMasterFile();
            selectCatalogue();
            manageSecurityEnvironment();
            switch (type) {
                case PIN1:
                    //TODO: check challenge
                    byte[] challenge = {0x3F, 0x4B, (byte) 0xE6, 0x4B, (byte) 0xC9, 0x06, 0x6F, 0x14, (byte) 0x8A, 0x39, 0x21, (byte) 0xD8, 0x7C, (byte) 0x94, 0x41, 0x40, (byte) 0x99, 0x72, 0x4B, 0x58, 0x75, (byte) 0xA1, 0x15, 0x78};
                    return transmitExtended(Util.concat(new byte[]{0x00, (byte) 0x88, 0x00, 0x00, 0x24}, challenge));
                case PIN2:
                    byte[] padded = AlgorithmUtils.addPadding(data, ellipticCurveCertificate);
                    return transmitExtended(Util.concat(new byte[]{0x00, 0x2A, (byte) 0x9E, (byte) 0x9A, (byte) padded.length}, padded));
                default:
                    throw new Exception("Unsupported");
            }
        } catch (Exception e) {
            throw new SignOperationFailedException(type, e);
        }
    }

    @Override
    public SparseArray<String> readPersonalFile() {
        selectMasterFile();
        selectCatalogue();
        selectPersonalDataFile();
        SparseArray<String> result = new SparseArray<>();
        for (byte i = 1; i <= 16; ++i) {
            byte[] data = readRecord(i);
            try {
                result.put(i, new String(data, "Windows-1252"));
            } catch (UnsupportedEncodingException e) {
                throw new TokenException(e);
            }
        }
        return result;
    }

    @Override
    public boolean changePin(PinType pinType, byte[] previousPin, byte[] newPin) {
        if (!isSecureChannel()) {
            throw new SecureOperationOverUnsecureChannelException("PIN replace is not allowed");
        }
        byte[] recv = transmit(Util.concat(new byte[]{0x00, 0x24, 0x00, pinType.value, (byte) (previousPin.length + newPin.length)}, previousPin, newPin));
        return checkSW(recv);
    }

    @Override
    public boolean unblockAndChangePin(PinType pinType, byte[] puk, byte[] newPin) {
        if (!isSecureChannel()) {
            throw new SecureOperationOverUnsecureChannelException("PIN replace is not allowed");
        }
        verifyPin(PinType.PUK, puk);

        blockPin(pinType, newPin.length);

        byte[] recv = transmit(Util.concat(new byte[]{0x00, 0x2C, 0x00, pinType.value, (byte) (puk.length + newPin.length)}, puk, newPin));
        return checkSW(recv);
    }

    @Override
    void selectMasterFile() {
        transmitExtended(new byte[]{0x00, (byte) 0xA4, 0x00, 0x0C});
    }

    @Override
    void selectCatalogue() {
        transmitExtended(new byte[]{0x00, (byte) 0xA4, 0x01, 0x0C, 0x02, (byte) 0xEE, (byte) 0xEE});
    }

    @Override
    void manageSecurityEnvironment() {
        transmitExtended(new byte[]{0x00, 0x22, (byte) 0xF3, 0x01});
    }

    @Override
    void selectPersonalDataFile() {
        transmitExtended(new byte[]{0x00, (byte) 0xA4, 0x02, 0x04, 0x02, (byte) 0x50, (byte) 0x44});
    }

}
