package br.com.felmanc.pointsofinterest.dtos;

public class PointOfInterestDTO {

    private String nome;
    private Long x;
    private Long y;

    // Construtores
    public PointOfInterestDTO() {}

    public PointOfInterestDTO(String nome, Long x, Long y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
