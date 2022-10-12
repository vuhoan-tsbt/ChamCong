export function updateAdminUser(context, adminUser) {
  context.commit("UPDATE_ADMIN_USER", adminUser);
}

export function resetAllState(context) {
  context.commit("RESET_ALL_STATE");
}
