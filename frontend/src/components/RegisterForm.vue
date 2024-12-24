<script>
export default {
  name: "RegisterForm",

  data() {
    return {
      login: "",
      password: "",
      password_confirm: ""
    }
  },

  methods: {
    handleRegister() {
      // register
    }
  }
}
</script>

<template>
  <div class="default-panel">
    <h2>РЕГИСТРАЦИЯ</h2>
    <form @submit.prevent="handleRegister">

      <div class="form-group">
        <input type="text" placeholder="Логин" id="login-input" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Пароль" id="password-input" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Подтверждение пароля" id="password-confirm-input" required>
      </div>

      <div class="form-group">
        <input type="submit" value="Зарегистрироваться" class="btn-primary">
      </div>
    </form>

    <button @click="$emit('switchToLogin')" class="btn-secondary">или войти</button>
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