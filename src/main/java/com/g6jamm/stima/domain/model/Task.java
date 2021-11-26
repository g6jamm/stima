package com.g6jamm.stima.domain.model;

import java.time.LocalDate;

public class Task {

    private final String NAME;
    private final double HOURS;
    private final int PRICE;
    private final LocalDate START_DATE;
    private final LocalDate END_DATE;
    private final Role ROLE;


    private Task(TaskBuilder taskBuilder) {
        this.NAME = taskBuilder.name;
        this.HOURS = taskBuilder.hours;
        this.PRICE = taskBuilder.price;
        this.START_DATE = taskBuilder.startDate;
        this.END_DATE = taskBuilder.endDate;
        this.ROLE = taskBuilder.role;

    }

    public String getName() {
        return NAME;
    }

    public int getPrice() {
        return PRICE;
    }

    public double getHours() {
        return HOURS;
    }

    public LocalDate getEND_DATE() {
        return END_DATE;
    }

    public LocalDate getSTART_DATE() {
        return START_DATE;
    }

    public Role getROLE() {
        return ROLE;
    }

    public static class TaskBuilder {
        private String name;
        private double hours;
        private int price;
        private LocalDate startDate;
        private LocalDate endDate;
        private Role role;

        public TaskBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder hours(double hours) {
            this.hours = hours;
            return this;
        }


        public TaskBuilder price(int price) {
            this.price = price;
            return this;
        }

        public TaskBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public TaskBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public TaskBuilder role(Role role) {
            this.role = role;
            return this;
        }

        private void reset() {
            this.name = null;
            this.hours = 0;
            this.price = 0;
            this.startDate = null;
            this.endDate = null;
            this.role = null;

        }

        public Task build() {
            Task newTask = new Task(this);
            reset();
            return newTask;
        }

    }
}
