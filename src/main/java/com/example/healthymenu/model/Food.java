package com.example.healthymenu.model;

import java.text.DecimalFormat;

public class Food {

        String name;
        String category;
        int weight;
        public Food(String name, String category, int weight) {
            this.name = name;
            this.category = category;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

    public String getWeightInOz() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format((float) weight * 0.035274f);
    }
        @Override
        public String toString() {
            return name + ";" + category + ";" + weight;
        }
    }

