
# Composer table

NB. we need a way to deal with "unknown", "various" or "trad." composers - most collections end up with a messy list of these on the U, V and T search pages! 

## Mandatory fields:
        - **Last name** (various spellings and special characters)
        - **First name** (should include option for single char, spellings and special characters)
        - **DOB** (which should include "unknown", year only, and "ca. " options)
        - **DOD** (which should include " - ", "unknown", year only, and "ca. " options - from this can be extrapolated copyright warnings 
            for composers who died less than 50/70 years ago, depending on orchestra territory. Perhaps an auto-generated warning icon for probable 
            in-copyright, with the user being able to choose their copyright territory)

    
## Optional fields:
        - **Middle name** (can be blank, multiple names, single character)
        - **Honorific Title(s)**
        - **Maiden name**
        - **Alternate name** (eg. Chevalier de Saint Georges/Joseph Bologne)
        
## Link with other APIs:
        - Icon to indicate presence on Composer Diversity Database, with link to their composer entry [Institute for Composer Diversity] (https://www.composerdiversity.com/)




# Work table
## Mandatory fields:
        - **Title** (accommodating alternate spellings, eg. Sacre, Rite etc)

            NEED TO ASCERTAIN WHETHER AN ARRANGEMENT OR REDUCTION AT THIS POINT, TO TAKE DIFFERENT INPUT/AUTOFILL PATHWAY

        - **Orchestration** (there are several different conventions for this. Could include toggle option, as in citation formatting software like Endnote. 
            Should also include option to add bumpers in the brass, wind doublings into separate players etc. Also it would be great if this was queriable, 
            or easily manipulated from user side. eg, certain works, Philharmonia used 2 horn bumpers and split the cor anglais doubling into a third player). Needs to also include text input options for things like ad. lib., offstage, optional, stage banda) 
        - **Composition/Publication date**
        - **Duration**
        - **Publisher** (including "self-published")

## Optional fields
        
        - **Version** (eg. the Bruckner Symphonies, reductions, concert versions of opera excerpts)
        - **Alternate Title**
        - **Arranger**
        - **reduction/arrangement orchestration**
        - **Additional composers** (ie Les Six, and more recent composer consortium works)
        - **Edition**
        - **Movements** (with durations?)
        - **Percussion requirements**
        - **Soloist requirements**
        - **Keys** (for concert arias)
        - **Chorus voicings**
        - **Borrowing options** Other database users with a full set available to borrow?

## Individual information kept only at local instance:
        
        - **Catalogue number**
        - **Owned or loaned** (radio)
        - **Bowed** (radio)
        - **String part numbers**
        - **Addable free text inputs** for orchestra's orchestration conventions, bowing details, ordering notes, long-term loan details, communication threads and 
            warnings connected to certain players/conductors (eg. Mackerras never plays this version of the suite - check notes in *this* version for his cuts)
        - **Cuts** free-text
        - **store (or link to local) PDFs of bowings/parts/scores/performance instructions**

## Poss links to other APIs
        - MOLA errata lists - probably behind paywall, so members only [MOLA] (https://mola-inc.org/p/about)
        - NYPhil bowing database [NYPhil Archives] (https://archives.nyphil.org/index.php/open-data and [NYPhil Archives github repo] https://github.com/nyphilarchive)

## Poss add-ons for users
        - Option to upload/share bowings, including graded tags for youth orch/amateur/prof etc
        - Option to upload/share errata        
        - Option to make work collection visible to other users to facilitate borrowing


        


## Thoughts
If a work has multiple versions/arrangements/reductions/keys, should the primary entry link to a separate table of associated works, rather than multiple entires in the works database?

Need to have a solution for changing symphony numbers like Dvorak's symphonies.

Is "in C minor" part of the title of Beethoven, Symphony no. 5, or in a separate optional field called key?

A user should have the option of multiple entries of an identical work (including same work, different publisher; and same work and publisher, different aged set, one bowed, one not, etc)

Most libraries will also have sets of family/festive concerts in booklets made in-house, which need cataloguing.

Audition excerpts








