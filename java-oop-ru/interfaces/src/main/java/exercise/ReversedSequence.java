package exercise;

// BEGIN

import lombok.Getter;

public class ReversedSequence implements CharSequence {
    @Getter
    private CharSequence charSequence;
    public ReversedSequence(String word) {
        charSequence = new StringBuilder(word).reverse();
    }

    @Override
    public int length() {
        return charSequence.length();
    }

    @Override
    public char charAt(int index) {
        return charSequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return subSequence(start, end);
    }

    /*public String toString() {
        return text.toString();
    }*/
}
// END
