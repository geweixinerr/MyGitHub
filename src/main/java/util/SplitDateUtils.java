package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期切片辅助类
 * **/
public class SplitDateUtils {

	public SplitDateUtils() {

	}

	public static void main(String[] args) throws ParseException {
        splitDate("2016-7-13","2016-7-21",8); //计算线程分配平均天数
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2016-10-11");
        Date endDate = sdf.parse("2016-11-09"); 
        
        recursionLoadData(startDate,endDate); //递归计算单个线程内每一个递归平均分配天数
	}
	
 	/**
 	 * 对时间进行分片处理.计算出数据存储月份,最大与最小相差月份数
 	 * @throws ParseException 
 	 * **/
 	private static Integer calculateDate(Date minDate,Date maxDate){
 	        Calendar cal = Calendar.getInstance();    
 	                 cal.setTime(minDate);    
 	        long minTimeLong = cal.getTimeInMillis();
 	                 cal.setTime(maxDate);    
 	        long maxTimeLong = cal.getTimeInMillis();         
 	        long between_days=(maxTimeLong-minTimeLong)/(1000*3600*24);  
 	       return Integer.parseInt(String.valueOf(between_days));           
 	}
 	
 	/**
 	 * 对时间进行分片处理.计算出每个线程平均分配天数
 	 * @param minCalDateStr-开始日期,maxCalDateStr-结束日期,threadNum-线程数
 	 * @throws ParseException 
 	 * **/
 	private static void splitDate(String minCalDateStr,String maxCalDateStr,int threadNum) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar minCal = Calendar.getInstance();
        Calendar maxCal = Calendar.getInstance();

        minCal.setTime(sdf.parse(minCalDateStr));
        maxCal.setTime(sdf.parse(maxCalDateStr));

		int monthDays = calculateDate(minCal.getTime(),maxCal.getTime()); //计算数据跨度天数
		
		int days = monthDays/threadNum;//平均每个线程分配天数
		int modDays = monthDays%threadNum; //剩余无法平均分配天数

		System.out.println("总天数:"+monthDays+",平均天数:"+days+",剩余天数："+modDays);
		
		for(int i=0;i<threadNum;i++){
			StringBuilder startDate = new StringBuilder(sdf.format(minCal.getTime()));
			System.out.println("------------"+i+"--------------->"+"切片开始时间:"+startDate.append(" 00:00:00").toString());
			minCal.add(Calendar.DAY_OF_MONTH, days);
			if(i == threadNum - 1){
				minCal.add(Calendar.DAY_OF_MONTH,modDays);
			}else{
				minCal.add(Calendar.DAY_OF_MONTH,-1);//递减1天
			}
			StringBuilder endDate = new StringBuilder(sdf.format(minCal.getTime()));
			System.out.println("-------------"+i+"------------>"+"切片结束时间:"+endDate.append(" 23:59:59").toString());
			minCal.add(Calendar.DAY_OF_MONTH,1);//递增1天
		}
	}
 	
 	/**
 	 * 对时间进行分片处理.计算出每个线程大切片下的小切片时间[合理利用索引]
 	 * @param date_1-开始日期,date_2-结束日期
 	 * @throws ParseException 
 	 * **/
	private static void recursionLoadData(Date date_1,Date date_2){
		  if(!(date_1.compareTo(date_2) > 0)){ //递归后分片最小时间超过最大时间,调整重置
			  Calendar cal = Calendar.getInstance();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  StringBuilder startDate = new StringBuilder();
			  cal.setTime(date_1);//格式转换
			  startDate.append(sdf.format(cal.getTime()));
			  startDate.append(" 00:00:00");
			  cal.add(Calendar.DAY_OF_MONTH,6);
			  StringBuilder endDate = new StringBuilder();

			  //计算切片开始时间累积+7,大于切片结束时间的,计算时间差修复.
			  if(cal.getTime().compareTo(date_2) > 0){
				  Integer dateNum = calculateDate(date_2,cal.getTime()); 
				  cal.add(Calendar.DAY_OF_MONTH,-dateNum);
				  endDate.append(sdf.format(cal.getTime()));
				  endDate.append(" 23:59:59");
			  }else{
				  endDate.append(sdf.format(cal.getTime()));
				  endDate.append(" 23:59:59");
			  }
              System.out.println("日期时间:"+startDate.toString()+",结束时间:"+endDate);
              cal.add(Calendar.DAY_OF_MONTH,1);//
			  recursionLoadData(cal.getTime(),date_2);//递归
		  } 		  
	}
}
