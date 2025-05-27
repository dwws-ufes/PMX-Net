<!-- src/layouts/DefaultLayout.vue -->
<template>
  <v-app>
      <v-app-bar app color="primary" dark>
       <router-link to="/" style="text-decoration: none; color: inherit;">
        <v-toolbar-title style="cursor: pointer">PMX-Net</v-toolbar-title>
      </router-link>  
     <v-spacer />
      <v-btn text to="/projetos">Projetos</v-btn>
      <v-btn text to="/informacoes">Informações</v-btn>
      <v-btn text to="/login">Login</v-btn>
    </v-app-bar>
    <v-main>
      <slot />
    </v-main>
  </v-app>
</template>

