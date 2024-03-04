// --== CS400 Fall 2022 File Header Information ==--
// Name: Nelson Liu
// CSL Username: nelsonl
// Email: naliu@wisc.edu
// Team: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
public class BDAirport implements IAirport{
    private String airport;

    BDAirport(String airport){
        this.airport = airport;
    }
    @Override
    public String getAirport() {

        return airport;
    }
}