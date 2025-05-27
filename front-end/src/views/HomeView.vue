<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>PMX-Net</v-toolbar-title>
      <v-spacer />
      <v-btn text to="/projetos">Projetos</v-btn>
      <v-btn text to="/informacoes">Informações</v-btn>
      <v-btn text to="/login">Login</v-btn>
    </v-app-bar>

    <v-main>
      <v-container class="fill-height" fluid>
        <v-row justify="center" align="center">
          <v-col cols="12" md="6">
            <v-img src="https://via.placeholder.com/600x300.png?text=PMX-Net" alt="Imagem do Projeto" contain />
          </v-col>
          <v-col cols="12" md="6">
            <h1 class="text-h4 font-weight-bold mb-4">Bem-vindo ao PMX-Net</h1>
            <p>
              Esta plataforma tem como objetivo mapear e analisar as interações entre os membros de equipes de projetos de software,
              oferecendo visualizações e insights sobre a comunicação e colaboração.
            </p>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <v-footer color="primary" app inset>
      <v-col class="text-center white--text">Desenvolvido por Felipe Oliveira © 2025</v-col>
    </v-footer>
  </v-app>
</template>

<script setup>
// Nenhum script necessário para essa tela estática
</script>