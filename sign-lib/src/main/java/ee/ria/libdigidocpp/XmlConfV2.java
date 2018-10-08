/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ee.ria.libdigidocpp;

public class XmlConfV2 extends ConfV2 {
  private transient long swigCPtr;

  protected XmlConfV2(long cPtr, boolean cMemoryOwn) {
    super(digidocJNI.XmlConfV2_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(XmlConfV2 obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        digidocJNI.delete_XmlConfV2(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public XmlConfV2(String path, String schema) {
    this(digidocJNI.new_XmlConfV2__SWIG_0(path, schema), true);
  }

  public XmlConfV2(String path) {
    this(digidocJNI.new_XmlConfV2__SWIG_1(path), true);
  }

  public XmlConfV2() {
    this(digidocJNI.new_XmlConfV2__SWIG_2(), true);
  }

  public int logLevel() {
    return digidocJNI.XmlConfV2_logLevel(swigCPtr, this);
  }

  public String logFile() {
    return digidocJNI.XmlConfV2_logFile(swigCPtr, this);
  }

  public String PKCS11Driver() {
    return digidocJNI.XmlConfV2_PKCS11Driver(swigCPtr, this);
  }

  public String proxyHost() {
    return digidocJNI.XmlConfV2_proxyHost(swigCPtr, this);
  }

  public String proxyPort() {
    return digidocJNI.XmlConfV2_proxyPort(swigCPtr, this);
  }

  public String proxyUser() {
    return digidocJNI.XmlConfV2_proxyUser(swigCPtr, this);
  }

  public String proxyPass() {
    return digidocJNI.XmlConfV2_proxyPass(swigCPtr, this);
  }

  public boolean proxyForceSSL() {
    return digidocJNI.XmlConfV2_proxyForceSSL(swigCPtr, this);
  }

  public boolean proxyTunnelSSL() {
    return digidocJNI.XmlConfV2_proxyTunnelSSL(swigCPtr, this);
  }

  public String digestUri() {
    return digidocJNI.XmlConfV2_digestUri(swigCPtr, this);
  }

  public String signatureDigestUri() {
    return digidocJNI.XmlConfV2_signatureDigestUri(swigCPtr, this);
  }

  public String ocsp(String issuer) {
    return digidocJNI.XmlConfV2_ocsp(swigCPtr, this, issuer);
  }

  public String TSUrl() {
    return digidocJNI.XmlConfV2_TSUrl(swigCPtr, this);
  }

  public String verifyServiceUri() {
    return digidocJNI.XmlConfV2_verifyServiceUri(swigCPtr, this);
  }

  public String PKCS12Cert() {
    return digidocJNI.XmlConfV2_PKCS12Cert(swigCPtr, this);
  }

  public String PKCS12Pass() {
    return digidocJNI.XmlConfV2_PKCS12Pass(swigCPtr, this);
  }

  public boolean PKCS12Disable() {
    return digidocJNI.XmlConfV2_PKCS12Disable(swigCPtr, this);
  }

  public boolean TSLAutoUpdate() {
    return digidocJNI.XmlConfV2_TSLAutoUpdate(swigCPtr, this);
  }

  public String TSLCache() {
    return digidocJNI.XmlConfV2_TSLCache(swigCPtr, this);
  }

  public boolean TSLOnlineDigest() {
    return digidocJNI.XmlConfV2_TSLOnlineDigest(swigCPtr, this);
  }

  public int TSLTimeOut() {
    return digidocJNI.XmlConfV2_TSLTimeOut(swigCPtr, this);
  }

  public void setProxyHost(String host) {
    digidocJNI.XmlConfV2_setProxyHost(swigCPtr, this, host);
  }

  public void setProxyPort(String port) {
    digidocJNI.XmlConfV2_setProxyPort(swigCPtr, this, port);
  }

  public void setProxyUser(String user) {
    digidocJNI.XmlConfV2_setProxyUser(swigCPtr, this, user);
  }

  public void setProxyPass(String pass) {
    digidocJNI.XmlConfV2_setProxyPass(swigCPtr, this, pass);
  }

  public void setProxyTunnelSSL(boolean enable) {
    digidocJNI.XmlConfV2_setProxyTunnelSSL(swigCPtr, this, enable);
  }

  public void setPKCS12Cert(String cert) {
    digidocJNI.XmlConfV2_setPKCS12Cert(swigCPtr, this, cert);
  }

  public void setPKCS12Pass(String pass) {
    digidocJNI.XmlConfV2_setPKCS12Pass(swigCPtr, this, pass);
  }

  public void setPKCS12Disable(boolean disable) {
    digidocJNI.XmlConfV2_setPKCS12Disable(swigCPtr, this, disable);
  }

  public void setTSLOnlineDigest(boolean enable) {
    digidocJNI.XmlConfV2_setTSLOnlineDigest(swigCPtr, this, enable);
  }

  public void setTSLTimeOut(int timeOut) {
    digidocJNI.XmlConfV2_setTSLTimeOut(swigCPtr, this, timeOut);
  }

}
