package org.itboys.commons.dto;

/**
 * 主机名或IP and 端口
 * @author ChenJunhui
 *
 */
public class HostAndPort {

	private int port;
	private String host;
	
	public HostAndPort(int port,String host){
		this.port=port;
		this.host=host;
	}

	public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}
}
