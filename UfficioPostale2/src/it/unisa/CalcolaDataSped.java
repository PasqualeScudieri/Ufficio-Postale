package it.unisa;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CalcolaDataSped {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDateTime dataDoamni= domaniAlle15();
		
		System.out.println(dataDoamni + " "+ dataDoamni.getDayOfWeek().toString());
		
		LocalDateTime dataAumentata= aumenta(LocalDateTime.of(2017, 10, 20, 19, 50));
	}
*/
	public static LocalDateTime aumenta(LocalDateTime date) {
		//ci devo pensare
		LocalDateTime nuova= date.plusMinutes(10);
		if(nuova.getHour()==20) {
			nuova=nuova.plusHours(24-20+15);
			nuova= nuova.minusMinutes(nuova.getMinute());
		}
		if(nuova.getDayOfWeek().equals(DayOfWeek.SATURDAY)) { 	
			nuova=nuova.plusHours(48);
		}else if(nuova.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			nuova=nuova.plusHours(24);
		}
		
		System.out.println(nuova + " "+ nuova.getDayOfWeek().toString());
		return nuova;
	}
	
	public static  LocalDateTime domaniAlle15() {
	
		LocalDateTime date= LocalDateTime.now();
		LocalDateTime dataDoamni= date.plusHours(24 - date.getHour() + 15);
		dataDoamni= dataDoamni.minusMinutes(date.getMinute());
		
		if(dataDoamni.getDayOfWeek().equals(DayOfWeek.SATURDAY)) { 	
			dataDoamni=dataDoamni.plusHours(48);
		}else if(dataDoamni.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			dataDoamni=dataDoamni.plusHours(24);
		}
		
		return dataDoamni;
	}
}
