<script>
export default {
  name: "LogoutButton",
  methods: {
    async handleLogout() {
      const resp = await fetch("/api/users/logout", {
        method: "GET",
        headers: {
          "Authorization": "Bearer " + localStorage.getItem("token"),
        }
      });
      if (resp.ok) {
        localStorage.removeItem("token");
        window.location.replace("/");
      } else {
        const errorData = await resp.json();
        this.$refs.errorMessage.showMessage(errorData.message || "Ошибка на сервере");
        return false;
      }
    }
  }
}
</script>

<template>
  <button class="btn-secondary" @click="handleLogout">
    Выйти
  </button>
</template>

<style scoped>
.btn-secondary {
  font-family: 'Montserrat', sans-serif;
  width: 30%;
}
</style>