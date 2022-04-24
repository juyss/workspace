package com.github.tangyi.user.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpUtil {

    public static String obtainIpFromHttpReq(HttpServletRequest request) {
        String ip = null;
        if (null == request) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
            } catch (Exception e) {
            } catch (Error e) {
            }
        } else {
            String ipAddresses = request.getHeader("x-forwarded-for");
            if (ipAddresses == null || ipAddresses.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("proxy-client-ip");
            }
            if (ipAddresses == null || ipAddresses.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("wl-proxy-client-ip");
            }
            if (ipAddresses == null || ipAddresses.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("http_client_ip");
            }
            if (ipAddresses == null || ipAddresses.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddresses = request.getHeader("x-real-ip");
            }
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ip = ipAddresses.split(",")[0];
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ipAddresses)) {
                ip = request.getRemoteAddr();
            }
        }
        if (!checkIp(ip)) {
            return null;
        }
        return ip;
    }

    public static boolean checkIp(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split("\\.").length != 4) {
            return false;
        }
        return true;
    }
}
