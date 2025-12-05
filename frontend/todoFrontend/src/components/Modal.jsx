import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { toggleModal } from "../store/slices/modalSlice";
import toast from "react-hot-toast";
import { addTask } from "../store/slices/todoSlice";
export default function Modal() {
  const dispatch = useDispatch();
  const { loading, error } = useSelector((store) => store.bag);
 
  const [bag, setBag] = useState({
    firstName: "",
    lastName: "",
    contactNo: "",
    task: "",
    email: "",
  });

  function checkForEmpty(obj) {
    for (let key in obj) {
      if (!obj[key]) return key;
    }
    return false;
  }
  function handleSubmit(e) {
    e.preventDefault();
    const result = checkForEmpty(bag);

    if (typeof result === "string") {
      toast.error(`${result} is required.`);
    } else {
      dispatch(addTask(bag))
        .unwrap()
        .then(() => {
          setBag({ firstName: "", lastName: "", contactNo: "", task: "", email: "" });
          toast.success("Task added successfully...");
          dispatch(toggleModal(false));
        })
        .catch((e) => {
          toast.error(error);
        });
    }
  }

  const trigger = (e) => {
    const { name, value } = e.target;

    setBag({ ...bag, [name]: value });
  };
  return (
    <>
      <div
        className="border absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-1 w-full h-full flex items-center justify-center"
        style={{ background: "rgba(0 , 0 , 0 , .7)" }}
      >
        <div className="w-[44%] min-h-[63%] rounded bg-gray-200 p-4">
          <header className="flex items-center justify-between border-b-[.1rem] border-brand pb-2 ">
            <h1 className="font-bold text-xl">
              Add <span className="text-brand">Details</span>
            </h1>
            <i
              className="fa-solid fa-xmark cursor-pointer text-center py-1"
              onClick={() => dispatch(toggleModal(false))}
            ></i>
          </header>

          {/* all field names will come here dude... */}

          <p className="text-gray-600 my-1 text-[10px] text-right tracking-wider">
            All fields are required.
          </p>
          <form
            className="mt-1 grid grid-cols-2 gap-3 relative"
            onSubmit={handleSubmit}
          >
            <input
              type="text"
              name="firstName"
              value={bag.firstName}
              placeholder="Enter your first name"
              className="border border-gray-300 rounded-lg px-3 focus:outline-none focus:ring-1 focus:ring-brand focus:border-brand"
              onChange={trigger}
            />

            <input
              type="text"
              name="lastName"
              value={bag.lastName}
              placeholder="Enter your last name"
              className="border border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-1 focus:ring-brand focus:border-brand"
              onChange={trigger}
            />

            <input
              type="number"
              name="contactNo"
              value={bag.contactNo}
              placeholder="Enter your contact number"
              className="border border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-1 focus:ring-brand focus:border-brand"
              onChange={trigger}
            />
            <input
              type="text"
              name="email"
              value={bag.email}
              placeholder="Enter your email"
              className="border border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-1 focus:ring-brand focus:border-brand"
              onChange={trigger}
            />
            <textarea
              id=""
              name="task"
              value={bag.task}
              placeholder="Enter your task"
              className="min-h-30 border border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-1 focus:ring-brand focus:border-brand col-span-2"
              onChange={trigger}
            ></textarea>
            <button
              type="submit"
              disabled={loading}
              className={`cursor-pointer left-1/2 absolute col-span-3 bg-brand text-white font-semibold py-2 rounded-lg w-full transform -translate-x-1/2 bottom-[-3rem] 
    ${
      loading
        ? "opacity-50 cursor-not-allowed"
        : "hover:bg-brand focus:outline-none"
    }`}
            >
              {loading ? "Submitting..." : "Submit"}
            </button>
          </form>
        </div>
      </div>
    </>
  );
}
