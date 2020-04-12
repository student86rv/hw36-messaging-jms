package ua.epam.messagingjms;

import java.util.UUID;

public class RequestMsg {

    private UUID id;

    private int firstNumber;

    private int secondNumber;

    public RequestMsg() {
    }

    public RequestMsg(UUID id, int firstNumber, int secondNumber) {
        this.id = id;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    public String toString() {
        return "RequestMsg{" +
                "id=" + id +
                ", firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                '}';
    }
}
