package co.istad.springwebmvc.dto;

public record ProductEditRequest(
        String name,
        Double price
) {
}
