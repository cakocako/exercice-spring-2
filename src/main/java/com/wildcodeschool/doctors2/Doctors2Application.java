package com.wildcodeschool.doctors2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@SpringBootApplication
public class Doctors2Application {

String[][] doctor = {
        {"9", "Christopher Eccleston", "Episode 13", "41"},
        {"10", "David Tennant", "Episode 47", "34"},
        {"11", "Matt Smith", "Episode 44", "27"},
        {"12", "Peter Capaldi", "Episode 40", "55"},
        {"13", "Jodie Whittaker", "Episode 11", "35"}
    };

    public static void main(String[] args) {
        SpringApplication.run(Doctors2Application.class, args);
    }
    @RequestMapping("/doctor/{id}")
    @ResponseBody
    public Doctor getDoctor(@PathVariable int id, @RequestParam(required = false) boolean details) {//ici on retourne un doctor avec number et name, RequestParam booléen

        if(id >= 9 && id <= 13){//si id est entre 9 et 13
            if(details == true){//comme détails = true
            return new ExtendedDoctor(doctor[id - 9][0], doctor[id - 9][1], doctor[id - 9][2],doctor[id - 9][3]);// id=ligne on part de 9 donc -9
            }
        return new Doctor(doctor[id - 9][0], doctor[id - 9][1]);
        }
        else if(id >= 1 && id <= 8){
        throw new ResponseStatusException(HttpStatus.SEE_OTHER);//renvoie page 303 see other
        }
            else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossibe de récupérer l'incarnation." + id);// id = numéro d'incarnation
            }   
    }

    class Doctor{//clic droit pour ajouter les getters(je récupère) et setters(je modifie)

        private String name;//attributs
        private String number;

        public Doctor(String number, String name){ //constructeur
            this.name = name;//this = référence de l'instance (vincent)
            this.number = number;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }   

    class ExtendedDoctor extends Doctor {//extension class Doctor afin d'y rajouter des attributs

        public ExtendedDoctor(String number, String name, String ageStart, String numberOfEpisode) {//constructeur
            super(number, name);//super = récupère les attributs présents dans Doctor 
            this.ageStart = ageStart;
            this.numberOfEpisode = numberOfEpisode;
            // TODO Auto-generated constructor stub
        }

        private String ageStart;
        private String numberOfEpisode;//nouveaux attributs

        public String getAgeStart() {
            return ageStart;
        }

        public void setAgeStart(String ageStart) {
            this.ageStart = ageStart;
        }

        public String getNumberOfEpisode() {
            return numberOfEpisode;
        }

        public void setNumberOfEpisode(String numberOfEpisode) {
            this.numberOfEpisode = numberOfEpisode;
        }

       
    }
  
        
}

   