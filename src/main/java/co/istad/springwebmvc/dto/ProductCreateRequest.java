package co.istad.springwebmvc.dto;

public record ProductCreateRequest(
        String name,
        Double price,
        Integer qty
) {
}
