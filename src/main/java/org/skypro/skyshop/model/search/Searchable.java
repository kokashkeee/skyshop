package org.skypro.skyshop.model.search;
import java.util.UUID;


public interface Searchable {
    String getSearchTerm ();
    String getSearchType ();
    default String getStringRepresentation(){
        return getSearchTerm() + " - " + getSearchType();
    }
    UUID getId();
}
