/*
 * Copyright © 2022 DNAnalyzer. Some rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are entirely responsible for the use of this application, including any and all activities that occur.
 * While DNAnalyzer strives to fix all major bugs that may be either reported by a user or discovered while debugging,
 * they will not be held liable for any loss that the user may incur as a result of using this application, under any circumstances.
 *
 * For further inquiries, please contact DNAnalyzer@piyushacharya.com
 */

package DNAnalyzer;

import DNAnalyzer.codon.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Reading frame data for the highest occurring codons.
 *
 * @author Piyush Acharya (@Verisimilitude11)
 * @author Nishant Vikramaditya (@Nv7-GitHub)
 * @version 1.2.1
 */
public class ReadingFrames {
    private final Map<String, Integer> codonCounts;
    private final CodonFrame codonFrame;

    /**
     * Constructor for the ReadingFrames class.
     *
     * @param {@link CodonFrame} frame for codon data
     * @category Codon
     */
    public ReadingFrames(final CodonFrame codonFrame) {
        this.codonCounts = new HashMap<>();
        this.codonFrame = codonFrame;
    }

    /**
     * Get codon counts in the specified reading frame
     *
     * @param dna The DNA sequence
     * @return The HashMap of codon counts in the specified reading frame
     * @category Codon
     */
    private void buildCodonMap(final String dna) {
        // reset the hashmap
        codonCounts.clear();
        // loop over DNA in steps of 3 and store the codon and its corresponding count
        for (int i = (int) codonFrame.getReadingFrame(); i < dna.length(); i += 3) {
            try {
                if (codonCounts.containsKey(dna.substring(i, i + 3))) {
                    codonCounts.put(dna.substring(i, i + 3), codonCounts.get(dna.substring(i, i + 3)) + 1);
                } else {
                    codonCounts.put(dna.substring(i, i + 3), 1);
                }
            } catch (final Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Method to filter through the codon counts found in the specified reading
     * frame based on the min
     * and max values
     *
     * @throws StringIndexOutOfBoundsException
     * @category Codon
     */
    public void printCodonCounts() throws StringIndexOutOfBoundsException {
        // Get codon counts for the dna in the specified reading frame
        buildCodonMap(codonFrame.getDna());

        // pretty print all the codon counts
        System.out.println(
                "Codons in reading frame " + codonFrame.getReadingFrame() + " (" + codonFrame.getMin() + "-"
                        + codonFrame.getMax() + " occurrences)" + ":");
        System.out.println("----------------------------------------------------");
        for (final Entry<String, Integer> entry : codonCounts.entrySet()) {
            if (codonCounts.get(entry.getKey()) >= codonFrame.getMin()
                    && codonCounts.get(entry.getKey()) <= codonFrame.getMax()) {
                System.out.println(entry.getKey().toUpperCase() + ": " + codonCounts.get(entry.getKey()));
            }
        }
    }
}
