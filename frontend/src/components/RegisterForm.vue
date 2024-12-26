<script>
import ErrorMessage from "@/components/ErrorMessage.vue";

export default {
  name: "RegisterForm",
  components: {ErrorMessage},

  data() {
    return {
      login: "",
      password: "",
      password_confirm: ""
    }
  },

  methods: {
    async handleRegister() {
      if (this.password === this.password_confirm) {
        try {
          const resp = await fetch("http://localhost:8080/api/users/register", {
            method: "POST",
            body: JSON.stringify({login: this.login, password: this.password}),
            headers: {
              "Content-Type": "application/json"
            }
          });

          if (resp.ok) {
            const data = await resp.json();
            const token = data.token;
            localStorage.setItem("token", token);
            window.location.replace("/main");
            return true;
          } else {
            const errorData = await resp.json();
            this.$refs.errorMessage.showMessage(errorData.error || "Ошибка на сервере");
            console.log(errorData);
            return false;
          }
        } catch(error) {
          this.$refs.errorMessage.showMessage("ошибка на сервере");
          console.log(error);
          return false;
        }
      } else {
        this.$refs.errorMessage.showMessage("Пароли не совпадают");
      }
    }
  }
}
</script>

<template>
  <div class="default-panel">
    <h2>РЕГИСТРАЦИЯ</h2>
    <form @submit.prevent="handleRegister">

      <div class="form-group">
        <input type="text" placeholder="Логин" id="login-input" v-model="login" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Пароль" id="password-input" v-model="password" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Подтверждение пароля" id="password-confirm-input" v-model="password_confirm" required>
      </div>

      <div class="form-group">
        <input type="submit" value="Зарегистрироваться" class="btn-primary">
      </div>
    </form>

    <button @click="$emit('switchToLogin')" class="btn-secondary">или войти</button>

    <ErrorMessage ref="errorMessage" />
  </div>
</template>

<style scoped>

h2 {
  margin-bottom: 20px;
  font-size: 1.8em;
  font-weight: bold;
  color: #7f7fff;
}

label {
  display: block;
  margin-bottom: 8px;
  font-size: 1em;
  color: #b0b0ff;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

</style>