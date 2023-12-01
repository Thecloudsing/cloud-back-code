package org.example.agreement;

public class UDPMessage {
    private int Displacement;
    private int Complement;

    private int length;

    private int[] hashcode;

    public UDPMessage(int displacement, int complement, int length, int[] hashcode) {
        Displacement = displacement;
        Complement = complement;
        this.length = length;
        this.hashcode = hashcode;
    }

    public int getDisplacement() {
        return Displacement;
    }

    public void setDisplacement(int displacement) {
        Displacement = displacement;
    }

    public int getComplement() {
        return Complement;
    }

    public void setComplement(int complement) {
        Complement = complement;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[] getHashcode() {
        return hashcode;
    }

    public void setHashcode(int[] hashcode) {
        this.hashcode = hashcode;
    }
}
