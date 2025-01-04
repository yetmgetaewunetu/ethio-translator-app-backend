const firstName = document.getElementById("first-name") as HTMLInputElement;

const lastName = document.getElementById("last-name") as HTMLInputElement;
const email = document.getElementById("email") as HTMLInputElement;
const schoolId = document.getElementById("schoolId") as HTMLInputElement;
const password = document.getElementById("password") as HTMLInputElement;
const confirmPsw = document.getElementById("confirm") as HTMLInputElement;
const submit = document.querySelector("button") as HTMLButtonElement;
const form = document.querySelector("form") as HTMLFormElement;

form.onsubmit = (e) => {
  e.preventDefault();
  const name: string = firstName.value + lastName.value;
  const clientEmail: string = email.value || "";
  const passwords: string = password.value || "";
  const confir: string = confirmPsw.value || "";
  console.log({ name, clientEmail, passwords, confir });
};
