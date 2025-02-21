package eng.software.reveste;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CheckoutDTO {

    @NotEmpty(message = "O nome completo é obrigatório.")
    private String nome;

    @NotEmpty(message = "O CEP é obrigatório.")
    @Size(min = 8, max = 8, message = "O CEP deve ter 8 dígitos.")
    private String cep;

    @NotEmpty(message = "O endereço é obrigatório.")
    private String endereco;

    @NotEmpty(message = "O número é obrigatório.")
    private String numero;

    private String complemento;

    @NotEmpty(message = "A cidade é obrigatória.")
    private String cidade;

    @NotEmpty(message = "O estado é obrigatório.")
    private String estado;

    @NotEmpty(message = "O número do cartão é obrigatório.")
    @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 dígitos.")
    private String cartao;

    @NotEmpty(message = "A validade é obrigatória.")
    @Size(min = 5, max = 5, message = "A validade deve estar no formato MM/AA.")
    private String validade;

    @NotEmpty(message = "O CVV é obrigatório.")
    @Size(min = 3, max = 3, message = "O CVV deve ter 3 dígitos.")
    private String cvv;

    @NotEmpty(message = "O nome do titular é obrigatório.")
    private String titular;

    @NotNull(message = "O parcelamento é obrigatório.")
    private Integer parcelas;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }
}