<script>
import LogoutButton from "@/components/LogoutButton.vue";

export default {
  name: "Header",
  components: {LogoutButton},
  methods: {
    checkAuth() {
      return localStorage.getItem("token") !== null;
    }
  }
}
</script>

<template>
  <header class="header">
    <div class="h-wrap">
      <h2>Сидимеков Дмитрий</h2>
      <h3>P3222 Вар 950</h3>
    </div>
    <div class="h-wrap">
      <h1>WEB LAB 4</h1>
    </div>
    <div class="h-wrap">
      <LogoutButton v-if="checkAuth"/>
    </div>
  </header>
</template>

<style scoped>


.header {
  display: flex;
  position: fixed;
  width: 100%;
  top:0;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 5px;
  background: linear-gradient(90deg, #3f3fff, #7f7fff);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  color: #ffffff;
}
.header .h-wrap:nth-child(2) {
  flex-grow: 1;
  text-align: center;
}

.h-wrap {
  flex: 1;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

h1 {
  font-size: 1.8em;
  font-weight: bold;
}

h1, h2, h3 {
  margin: 3px 0;
}
</style>