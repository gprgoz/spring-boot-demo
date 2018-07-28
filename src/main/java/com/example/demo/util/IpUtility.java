package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class IpUtility {
	private static Logger logger = LoggerFactory.getLogger(IpUtility.class);

	//获取本地IP地址，优先取外网地址
	public static String getLocalIpAddr(){
		
		LinkedList<String> ipAddrs = new LinkedList<String>();
		
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            
			
			while (netInterfaces.hasMoreElements()) {
                NetworkInterface nif = netInterfaces.nextElement();
                Enumeration<InetAddress> iparray = nif.getInetAddresses();
                while (iparray.hasMoreElements()) {
                   String ipAddr = iparray.nextElement().getHostAddress();
                   if (ipAddr != null && ipAddr.matches("(\\d{1,3}\\.){3}\\d{1,3}"))
                	   ipAddrs.push(ipAddr);
                   //System.out.println(ipAddr);
                }
            }
			
        } catch (Exception e) {
            return null;
        }
        
        //查找外网ip
        for (String ipAddr : ipAddrs){
        	if (!isInnerIP(ipAddr))
        		return ipAddr;
        }
        
       //查找第一个非127.0.0.1的地址
        for (String ipAddr : ipAddrs){
        	if (!"127.0.0.1".equals(ipAddr))
        		return ipAddr;
        }
        
        return null;
	}
	
    public static boolean isInnerIP(String ipAddress){   
            boolean isInnerIp = false;   
            long ipNum = getIpNum(ipAddress);   
            /**  
            私有IP：A类  10.0.0.0-10.255.255.255  
                   B类  172.16.0.0-172.31.255.255  169.254.0.0到169.254.255.255
                   C类  192.168.0.0-192.168.255.255  
            当然，还有127这个网段是环回地址  
            **/  
            long aBegin = getIpNum("10.0.0.0");   
            long aEnd = getIpNum("10.255.255.255");   
            long bBegin = getIpNum("172.16.0.0");   
            long bEnd = getIpNum("172.31.255.255");   
            long cBegin = getIpNum("192.168.0.0");   
            long cEnd = getIpNum("192.168.255.255");   
            long dBegin = getIpNum("169.254.0.0");   
            long dEnd = getIpNum("169.254.255.255");   
            
            isInnerIp = isInner(ipNum,aBegin,aEnd) || 
            			isInner(ipNum,bBegin,bEnd) || 
            			isInner(ipNum,cBegin,cEnd) || 
            			isInner(ipNum,dBegin,dEnd) || 
            			ipAddress.equals("127.0.0.1");   
            return isInnerIp;              
    }  

    private static long getIpNum(String ipAddress) {   
        String [] ip = ipAddress.split("\\.");   
        long a = Integer.parseInt(ip[0]);   
        long b = Integer.parseInt(ip[1]);   
        long c = Integer.parseInt(ip[2]);   
        long d = Integer.parseInt(ip[3]);   
      
        long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;   
        return ipNum;   
    }  


    private static boolean isInner(long userIp,long begin,long end){   
         return (userIp>=begin) && (userIp<=end);   
    }  

    
    public static String getRemoteAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
	    String headers[] = {
	    		"X-Real-IP",
	    		"X-Forwarded-For",
	    		"Proxy-Client-IP",
	    		"HTTP_CLIENT_IP",
	    		"HTTP_X_FORWARDED_FOR"
	    };
		//依次取以上request Header域
	    String header = null;
	    for (int i = 0; i < headers.length; ++i){
			ip = request.getHeader(headers[i]);
			if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)){
				header = headers[i];
				break;
			}
		}
	    //X-Forwarded-For头带的地址类型为 clientip, proxy1, proxy2, 取第一项ip即可
	    if (ip != null && ("X-Forwarded-For".equalsIgnoreCase(header) || "HTTP_X_FORWARDED_FOR".equalsIgnoreCase(header))){
	    	int clippos = ip.indexOf(",");
	    	if (clippos > 0){
	    		ip = ip.substring(0, clippos);
	    	}
	    }
	    
	    //默认为request.getRemoteAddr
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
	    return ip;
	}
    
    
    public static List<String> getIpAddrs() {
    	List<String> IPs = new ArrayList<String>();
		Enumeration<NetworkInterface> allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			logger.error("不能够拿到本机的ip地址",e);
			logger.error("不能够拿到本机的ip地址,返回一个size=0 的 列表");
			return IPs;
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			Enumeration<?> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address && ip.getHostAddress().indexOf(".") != -1) {
					IPs.add(ip.getHostAddress());
				}
			}
		}
		return IPs;
	}

    public static void main(String argv[]) throws ParseException{
    	System.out.println(getLocalIpAddr());
    }

}
