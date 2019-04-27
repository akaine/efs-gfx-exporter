/*
 * Copyright (C) 2015 joulupunikki joulupunikki@gmail.communist.invalid.
 *
 *  Disclaimer of Warranties and Limitation of Liability.
 *
 *     The creators and distributors offer this software as-is and
 *     as-available, and make no representations or warranties of any
 *     kind concerning this software, whether express, implied, statutory,
 *     or other. This includes, without limitation, warranties of title,
 *     merchantability, fitness for a particular purpose, non-infringement,
 *     absence of latent or other defects, accuracy, or the presence or
 *     absence of errors, whether or not known or discoverable.
 *
 *     To the extent possible, in no event will the creators or distributors
 *     be liable on any legal theory (including, without limitation,
 *     negligence) or otherwise for any direct, special, indirect,
 *     incidental, consequential, punitive, exemplary, or other losses,
 *     costs, expenses, or damages arising out of the use of this software,
 *     even if the creators or distributors have been advised of the
 *     possibility of such losses, costs, expenses, or damages.
 *
 *     The disclaimer of warranties and limitation of liability provided
 *     above shall be interpreted in a manner that, to the extent possible,
 *     most closely approximates an absolute disclaimer and waiver of
 *     all liability.
 *
 */


/**
 * A class which implements a simple counter. Intended for representing
 * positions in FileChannels.
 *
 * @author joulupunikki
 */
public class Counter {

    long counter;

    /**
     * Creates a new Counter and sets it to zero.
     */
    public Counter() {
        counter = 0;
    }

    /**
     * Adjusts the Counter by increment and returns the old Counter value.
     *
     * @param increment the amount by which the Counter is adjusted.
     * @return the old Counter value.
     */
    public long getSet(long increment) {
        long ret_val = counter;
        counter += increment;
        return ret_val;
    }

}
