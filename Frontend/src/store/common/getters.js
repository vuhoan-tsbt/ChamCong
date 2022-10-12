export function getME(state) {
    return state.adminUser;
}

export function getTOKEN(state) {
    if (state.adminUser) return state.adminUser.token;
    return null;
}
