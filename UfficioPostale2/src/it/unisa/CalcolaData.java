package it.unisa;

import java.util.GregorianCalendar;

public class CalcolaData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(new Date());
		//System.out.println(new GregorianCalendar());
		//GregorianCalendar cal= new GregorianCalendar();
		//cal.add(GregorianCalendar.HOUR_OF_DAY, 24);
		//System.out.println(cal);
		GregorianCalendar cal= new GregorianCalendar();
		stampa(cal);
		cal.add(GregorianCalendar.HOUR, 24 - cal.get(GregorianCalendar.HOUR_OF_DAY) + 15);
		cal.set(GregorianCalendar.MINUTE, 0);
		stampa(cal);
		
		if(cal.get(GregorianCalendar.DAY_OF_WEEK)== GregorianCalendar.SATURDAY) {
			cal.add(GregorianCalendar.HOUR, 48);
		}else if(cal.get(GregorianCalendar.DAY_OF_WEEK)== GregorianCalendar.SUNDAY) {
			cal.add(GregorianCalendar.HOUR, 24);
		}
		
		stampa(cal);
	}
	
	private static void stampa(GregorianCalendar cal) {
		System.out.print (cal.get(GregorianCalendar.DAY_OF_MONTH )+"/" + cal.get(GregorianCalendar.MONTH )+ "/"+ cal.get(GregorianCalendar.YEAR));
		
		switch (cal.get(GregorianCalendar.DAY_OF_WEEK)) {
			case GregorianCalendar.MONDAY:	
				System.out.print(" Lunedì ");
				break;
			case GregorianCalendar.TUESDAY:	
				System.out.print(" Martedi ");
				break;
			case GregorianCalendar.WEDNESDAY:	
				System.out.print(" Mercoledì ");
				break;
			case GregorianCalendar.THURSDAY:	
				System.out.print(" Giovedì ");
				break;
			case GregorianCalendar.FRIDAY:	
				System.out.print(" Venerdì ");
				break;
			case GregorianCalendar.SATURDAY:	
				System.out.print(" Sabato ");
				break;
			case GregorianCalendar.SUNDAY:	
				System.out.print(" domenica ");
				break;
		}
		
		System.out.println(cal.get(GregorianCalendar.HOUR_OF_DAY) + ":"+ cal.get(GregorianCalendar.MINUTE));
	}

}
