package ua.epam.messagingjms;

import java.util.UUID;

public class ResponseMsg {

    private UUID id;

    private int summ;

    public ResponseMsg() {
    }

    public ResponseMsg(UUID id, int summ) {
        this.id = id;
        this.summ = summ;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "id=" + id +
                ", summ=" + summ +
                '}';
    }
}
