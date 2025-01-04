const BASE_URL = "localhost:4000";
const registerUser = async (body) => {
  const response = await fetch(BASE_URL + "/auth/register", {
    method: "POST",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify(body),
  });
};
