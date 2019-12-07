package festivalmanager.festival;

import javax.validation.constraints.NotNull;

import festivalmanager.ticket.Sort;

public class FestivalIdForm {

    @NotNull
    private final Long id;
    @NotNull
    private final Sort sort;
  
    public FestivalIdForm(Long id, String sort_str) {
      this.id = id;
      this.sort = stringToSort(sort_str);
    }

    public Sort stringToSort(String string){
      if("CAMPINGTICKET".equals(string)){
        return Sort.CAMPINGTICKET;
      }
      else{
        return Sort.DAYTICKET;
      }
    }
  
    public Long getId() {
      return this.id;
    }

    public Sort getSort() {
      return this.sort;
    }
  }