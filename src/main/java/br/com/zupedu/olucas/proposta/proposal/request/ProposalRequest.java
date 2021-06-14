package br.com.zupedu.olucas.proposta.proposal.request;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import br.com.zupedu.olucas.proposta.proposal.validators.CPFOrCNPJ;
import br.com.zupedu.olucas.proposta.validators.UniqueValue;

import javax.validation.constraints.*;

public class ProposalRequest {

    @NotNull
    @CPFOrCNPJ
    @UniqueValue(entity = Proposal.class, attribute = "document", message = "Already exist document for this proposal")
    private String document; //cpf or cnpj
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    @Positive
    private Double salary;

    public ProposalRequest(String document, String email, String name, String address, Double salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal convertRequestToEntity() {
        return new Proposal(this.document, this.email, this.name, this.address,
                this.salary);
    }
}
