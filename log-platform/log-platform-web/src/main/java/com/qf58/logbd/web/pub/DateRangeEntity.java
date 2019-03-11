package com.qf58.bd.web.pub;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/8/27 15:08
 * Time: 14:15
 */
public class DateRangeEntity implements Serializable {

    private static final long serialVersionUID = -4929717179571105141L;

    private LocalDate start;

    private LocalDate end;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }


}
