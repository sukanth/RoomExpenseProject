package com.room.EMS.Beans;
/**
 * @author sukanthgunda
 * Description : Simple POJO class for email functionality .
 */
import org.springframework.stereotype.Component;

@Component
public class email_bean {
    String toAddress;

    public String getToAddress() {
	return toAddress;
    }

    public void setToAddress(String toAddress) {
	this.toAddress = toAddress;
    }
}
