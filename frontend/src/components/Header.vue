<script>
export default {
  name: "Header"
}
</script>

<template>
  <header class="header">
    <h1>WEB LAB 4</h1>
    <h2>Сидимеков Дмитрий</h2>
    <h3>P3222 Вар 950</h3>
  </header>
</template>

<style scoped>
.header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 5px;
  background: linear-gradient(90deg, #3f3fff, #7f7fff);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  color: #ffffff;
  animation: fadeIn 1s ease-in-out;
}

h1 {
  font-size: 1.8em;
  font-weight: bold;
}

h1, h2, h3 {
  margin: 15px;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>