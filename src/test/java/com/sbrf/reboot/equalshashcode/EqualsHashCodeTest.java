package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class EqualsHashCodeTest {

     class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {
            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
             return true;

            // Сравнение null и сравнение класса
            // Для каждого экземпляра o: o.equals(null) должно возвращать false
            if (o == null || o.getClass() != this.getClass())
                return false;

            Car car = (Car)o;
            // Симметричность: Если a.equals(b) == true, то и b.equals(a) должно возвращать true
            return (Objects.equals(model, car.model))
                    && (Objects.equals(color, car.color))
                    && (Objects.equals(releaseDate,car.releaseDate)
                    && (maxSpeed == car.maxSpeed));
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;

            result = prime * result + ((model == null) ? 0 : model.hashCode());
            result = prime * result + ((color == null) ? 0 : color.hashCode());
            result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
            result = prime * result + maxSpeed;

            return result;
        }
     }

    @Test
    public  void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(),car2.hashCode());

    }

    @Test
    public void failEqualsHashCode(){
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(),car2.hashCode());

    }

    // Рефлексивность - объект равен самому себе
    // x.equals(x) ---> true
    @Test
    public void assertEqualsReflexivity() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Assertions.assertEquals(car1, car1);
    }

    // Симметричность - для каждого экземпляра x и y: x.equals(y) должен
    // возвращать true только тогда, когда y.equals(x) возвращает true
    @Test
    public void assertEqualsSymmetry() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1, car2);
        Assertions.assertEquals(car2, car1);
    }

    // Транзитивность: Для каждого экземпляра x, y и z должно выполнятся условие:
    // если x.equals(y) возвращает true и y.equals(z) возращает true, тогда x.equals(z) должно возращать true
    @Test
    public void assertTrueTransitivity() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car2.maxSpeed = 10;

        Car car3 = new Car();
        car3.model = "Mercedes";
        car3.color = "black";
        car3.releaseDate = new GregorianCalendar(2020, Calendar.JANUARY, 25);
        car3.maxSpeed = 10;

        Assertions.assertTrue(car1.equals(car2));
        Assertions.assertTrue(car2.equals(car3));
        Assertions.assertTrue(car1.equals(car3));
    }

    //  Согласованность: Для каждого экземпляра x повторное выполнение
    //  x.hashCode() должно возвращать одинаковый хэш
    @Test
    public void assertEqualsConsistency() {
        Car car = new Car();
        car.model = "Audi";
        car.color = "black";
        car.releaseDate = new GregorianCalendar(2019, Calendar.JANUARY,1);
        car.maxSpeed = 10;

        Assertions.assertEquals(car.hashCode(), car.hashCode());
    }

    // Сравнение null
    @Test
    public void assertFalseNull() {
        Car car = new Car();
        car.model = "Audi";
        car.color = "black";
        car.releaseDate = new GregorianCalendar(2019, Calendar.JANUARY,1);
        car.maxSpeed = 10;

        Assertions.assertFalse(car.equals(null));
    }


}
