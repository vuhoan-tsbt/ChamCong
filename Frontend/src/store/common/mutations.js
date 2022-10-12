const getDefaultState = () => ({
    adminUser: null,
});

export const UPDATE_ADMIN_USER = (state, adminUser) => {
    state.adminUser = adminUser;
};

export const RESET_ALL_STATE = (state) => {
    Object.assign(state, getDefaultState());
};
