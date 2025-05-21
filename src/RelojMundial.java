import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class RelojMundial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String respuesta = "si";

        while (respuesta.equalsIgnoreCase("si")) {
            // hora local
            LocalDateTime ahora = LocalDateTime.now();
            int hora = ahora.getHour();
            int minuto = ahora.getMinute();

            System.out.println("Hora local: " + hora + ":" + (minuto < 10 ? "0" + minuto : minuto));

            // saludo segun la hora
            if (hora >= 6 && hora <= 11) {
                System.out.println("Saludo: Good morning!");
            } else if (hora >= 12 && hora <= 17) {
                System.out.println("Saludo: Good afternoon!");
            } else if (hora >= 18 && hora <= 21) {
                System.out.println("Saludo: Good evening!");
            } else {
                System.out.println("Saludo: Time to rest...");
            }

            // dia de la semana
            DayOfWeek dia = ahora.getDayOfWeek();
            System.out.println("Hoy es: " + dia.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-ES")));

            // pedir ciudad
            System.out.println("Elegi una ciudad (Tokio, Londres, NuevaYork, Montevideo, Mexico): ");
            String ciudad = sc.nextLine().toLowerCase();

            ZoneId zona = null;

            if (ciudad.equals("tokio")) {
                zona = ZoneId.of("Asia/Tokyo");
            } else if (ciudad.equals("londres")) {
                zona = ZoneId.of("Europe/London");
            } else if (ciudad.equals("nueva_york")) {
                zona = ZoneId.of("America/New_York");
            } else if (ciudad.equals("montevideo")) {
                zona = ZoneId.of("America/Montevideo");
            } else if (ciudad.equals("mexico")) {
                zona = ZoneId.of("America/Mexico_City");
            } else {
                System.out.println("Ciudad no reconocida.");
                continue;
            }

            ZonedDateTime enCiudad = ZonedDateTime.now(zona);
            System.out.println("Hora en " + ciudad + ": " +
                enCiudad.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) +
                " " + enCiudad.getHour() + ":" + (enCiudad.getMinute() < 10 ? "0" + enCiudad.getMinute() : enCiudad.getMinute()));

            // diferencia horaria
            ZoneOffset localOffset = ZonedDateTime.now().getOffset();
            ZoneOffset offsetCiudad = enCiudad.getOffset();
            int diferenciaHoras = offsetCiudad.getTotalSeconds() / 3600 - localOffset.getTotalSeconds() / 3600;

            if (diferenciaHoras >= 0) {
                System.out.println("Diferencia horaria: +" + diferenciaHoras + " horas");
            } else {
                System.out.println("Diferencia horaria: " + diferenciaHoras + " horas");
            }

            System.out.println("¿Querés consultar otra ciudad? (si/no): ");
            respuesta = sc.nextLine().toLowerCase();
        }

        System.out.println("Programa terminado.");
        sc.close();
    }
}