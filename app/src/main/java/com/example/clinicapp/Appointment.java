package com.example.clinicapp;

public class Appointment {
        private int id;
        private String name;
        private String surname;
        private String number;
        private String date;
        private String time;
        private String clinic;

        public Appointment(String name, String surname, String number, String date, String time, String clinic) {
            this.name = name;
            this.surname = surname;
            this.number = number;
            this.date = date;
            this.time = time;
            this.clinic = clinic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getClinic(){
            return clinic;
        }

        public void setClinic(String clinic){
            this.clinic = clinic;
        }
    }