import {configureStore} from '@reduxjs/toolkit';
import modalSlice from './slices/modalSlice';
import todoSlice from './slices/todoSlice';
const store = configureStore({
    reducer : {
        modalStatus : modalSlice,
        bag : todoSlice
    }
});

export default store;