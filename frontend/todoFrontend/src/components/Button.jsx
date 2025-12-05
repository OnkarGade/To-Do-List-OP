export default function Button({func}) {
  return (
    <button
    onClick={func ? func : ""}
      className="
          px-6 py-[.4rem]
          bg-brand
          text-white 
          font-semibold 
          rounded 
          shadow-md 
          hover:bg-brand 
            cursor-pointer
        "
    >
      Add Task
    </button>
  );
}
