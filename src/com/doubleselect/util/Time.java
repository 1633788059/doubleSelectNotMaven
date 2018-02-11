package com.doubleselect.util;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Random;  


	public class Time {  
	    private SimpleDateFormat sdf = null;  
	    private String ip = null;  
	    public Time(){  
	                      
	    }  
	    public Time(String ip){  
	        this.ip = ip;         
	    }  
	    public String getIPTimeRand(){  
	        StringBuffer buf = new StringBuffer();  
	        if(this.ip!=null){  
	            String s[] = this.ip.split("\\.");  
	            for(int i=0;i<s.length;i++){  
	                buf.append(this.addZero(s[i],3));       //不够三位数字的要补0                  
	            }  
	        }  
	        buf.append(this.getTimeStamp());            //取得时间戳  
	        Random r = new Random();  
	        for(int i=0;i<3;i++){  
	            buf.append(r.nextInt(10));              //增加一个随机数         
	        }  
	        return buf.toString();  
	    }  
	    private String addZero(String str,int len){  
	        StringBuffer s = new StringBuffer();  
	        s.append(str);  
	        while(s.length()<len){  
	            s.insert(0,"0");              
	        }  
	        return s.toString();  
	    }  
	    public String getDate(){  
	        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
	        return this.sdf.format(new Date());  
	    }  
	    public String getTimeStamp(){ 
	    	/*this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	        return this.sdf.format(new Date()); */ 
	    	/**
	    	 * 时间戳转换位String
	    	 */
	    	/* Long timestamp = Long.parseLong("1458373579")*1000;    
	    	 String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(timestamp));
	    	 return date;*/
	    	Date data=new Date();
	    	//String ss=data.getTime()/1000+"";
	    	String ss=System.currentTimeMillis()/1000+"";
	    	return ss;
	    }  
	   
	    public static void main(String [] args){
	    	Time time=new Time();
	    	//System.out.println(time.getIPTimeRand());
	    }
	}  
