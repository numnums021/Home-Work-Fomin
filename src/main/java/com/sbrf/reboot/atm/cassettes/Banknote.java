package com.sbrf.reboot.atm.cassettes;

import lombok.Data;

@Data
public class Banknote {
    @Data
    public static class OneHundred extends Banknote {
    }

    @Data
    public static class OneThousand extends Banknote {
    }

}
