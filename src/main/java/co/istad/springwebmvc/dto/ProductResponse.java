package co.istad.springwebmvc.dto;

public record ProductResponse(String uuid,
                              String name,
                              Double price,
                              Integer qty) {


}
