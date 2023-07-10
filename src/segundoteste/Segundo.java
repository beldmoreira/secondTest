package segundoteste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Segundo {
    public static final String APROVADO = "Aprovado";
    public static final String RECEBIDO = "Recebido";
    public static final String QUALIFICADO = "Qualificado";
    public int id;

    public HashMap<Integer,Candidato> info = new HashMap<>();

    public int iniciarProcesso(String nome) throws Exception {
        validarNome(nome);

        var candidato = new Candidato();
        candidato.id = id;
        candidato.nome = nome;
        candidato.status = RECEBIDO;
        info.put(candidato.id,candidato);
        id++;
        return candidato.id;
    }

    private void validarNome(String nome) throws Exception {
        if(nome == null|| nome.isEmpty()){
            throw new Exception("Nome inválido");
        }
        for (Candidato candidato : info.values()) {
            if (nome.equals(candidato.nome)) {
                throw new Exception("Candidato já participa do processo.");
            }
        }
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        validarId(codCandidato);
        var candidato = info.get(codCandidato);
        if(!candidato.status.equals(RECEBIDO)){
            throw new Exception("Candidato não encontrado");
        }
        candidato.status = QUALIFICADO;
    }

    private void validarId(int codCandidato) throws Exception {
        if(!info.containsKey(codCandidato)){
            throw new Exception("Candidato não encontrado");
        }
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        validarId(codCandidato);
        info.remove(codCandidato);
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        validarId(codCandidato);
        var candidato = info.get(codCandidato);
        return candidato.status;
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        validarId(codCandidato);
        var candidato = info.get(codCandidato);
        if(!candidato.status.equals(QUALIFICADO)){
            throw new Exception("Candidato não encontrado");
        }
        candidato.status = APROVADO;
    }

    public List<String> obterAprovados(){
        var aprovados = new ArrayList<String>();
        for (Candidato candidato : info.values()) {
            if (candidato.status.equals(APROVADO)) {
              aprovados.add(candidato.nome);
            }
        }
        return aprovados;
    }
}
