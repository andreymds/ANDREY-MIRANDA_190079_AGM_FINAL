package com.example.listpersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listpersonagens.R;
import com.example.listpersonagens.dao.PersonagemDAO;
import com.example.listpersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.listpersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //título da aba
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Edita Personagem";
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";

    //variáveis que correspondem aos campos do formulário
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private EditText campoCep;
    private EditText campoRg;
    private EditText campoGenero;
    private final PersonagemDAO dao = new PersonagemDAO(); //nova classe
    private Personagem personagem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //cria um novo item na lista ao salvar o formulário
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //vai ao item da lista
        int itemId = item.getItemId(); //exibe as infos do item selecionado

        //ao clicar em salvar fecha a aba do formulário e vai à lista
        if(itemId == R.id.activity_formulario_personagem_menu_salvar){
            finalizarFomulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //chamado ao iniciar o programa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        //métodos chamados ao iniciar a aplicação
        inicializacaoCampos();
        configBotaoAdd();
        carregaPersonagem();
    }

    private void carregaPersonagem() {
        Intent dados = getIntent(); //instancia os dados
        if(dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);

            //"dados" busca as informações do personagem
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem(); //caso user add personagem sem dados
        }
    }

    private void preencheCampos() {
        //define para que variável vai cada campo do forms preenchido
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
        campoTelefone.setText(personagem.getTelefone());
        campoEndereco.setText(personagem.getEndereco());
        campoCep.setText(personagem.getCep());
        campoRg.setText(personagem.getRg());
        campoGenero.setText(personagem.getGenero());
    }

    private void configBotaoAdd() {
        Button botaoSalvar = findViewById(R.id.button_salvar); //busca o botão no xml

        /*Instanciando uma View*/
        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Sobrescrevendo Método de Click*/
            @Override
            public void onClick(View v) {
                finalizarFomulario();
            }
        });
    }

    private void finalizarFomulario() { //condições de finalização do formulário
        ArmazenaValoresClick();
        if(personagem.IdValido()){
            dao.editar(personagem);
            finish();
        } else{
            dao.salva(personagem);
        }
        finish();
    }

    private void inicializacaoCampos() {
        /*Vinculando os objetos do xml com as variáveis*/
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
        campoTelefone = findViewById(R.id.edittext_telefone);
        campoEndereco= findViewById(R.id.edittext_endereco);
        campoCep= findViewById(R.id.edittext_cep);
        campoRg= findViewById(R.id.edittext_rg);
        campoGenero= findViewById(R.id.edittext_genero);

        //máscaras para formatação dos inputs
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN"); //formato da máscara
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento); //linka o formato com o campo
        campoNascimento.addTextChangedListener(mtwNascimento);

        SimpleMaskFormatter smfTelefone = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(campoTelefone, smfTelefone);
        campoTelefone.addTextChangedListener(mtwTelefone);

        SimpleMaskFormatter smfRg = new SimpleMaskFormatter("NN.NNN.NNN-N");
        MaskTextWatcher mtwRg = new MaskTextWatcher(campoRg, smfRg);
        campoRg.addTextChangedListener(mtwRg);

        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(campoCep, smfCep);
        campoCep.addTextChangedListener(mtwCep);
    }

    private void ArmazenaValoresClick(){
        /*Pega informação das caixas de texto e armazena os valores nas variáveis*/
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String cep = campoCep.getText().toString();
        String rg = campoRg.getText().toString();
        String genero = campoGenero.getText().toString();

        //possibilita edição dos personagens salvos
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setTelefone(telefone);
        personagem.setEndereco(endereco);
        personagem.setCep(cep);
        personagem.setRg(rg);
        personagem.setGenero(genero);
    }
}