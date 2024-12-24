<script setup>

import Header from "@/components/Header.vue";
</script>

<template>
  <Header/>
  <div class="app">
    <router-view />
  </div>
  <div class="img-background"/>
</template>

<style scoped>
.app {
  margin-top:100px;
  height: 100%;
  min-height: 100%;
}

.img-background {
  position: fixed;
  top:0;
  width: 100%;
  height: 100%;
  background-image: url('@/assets/img/background.png');
  background-size: cover;
  background-position: 0 20%;
  filter: blur(5px);
  z-index: -1;
}

.app::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: 0 20%;
  filter: blur(5px);
  z-index: -1;
}
</style>
