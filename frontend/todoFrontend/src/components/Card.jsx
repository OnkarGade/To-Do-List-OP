export default function Card(props) {
    const obj = props.obj;
  return (
    <>
      <main className="overflow-hidden w-[20rem] h-44 grid grid-rows-[1fr_2.5fr] rounded-2xl shadow-xl">
        <nav className="flex items-center justify-between pb-1 bg-brand text-white px-3">
          <h2 className="font-bold text-xl">{obj.fname}</h2>
          <i className="fa-solid fa-user cursor-pointer text-black"></i>
        </nav>

        <section className="bg-white p-2 relative">
          <h2>{obj.task}</h2>
          <div className="absolute bottom-1 right-3 flex items-center justify-between gap-1">
            <i className="fa-regular fa-pen-to-square px-4 py-3 cursor-pointer"></i>
            <i className="fa-solid fa-trash-can px-4 py-3 cursor-pointer"></i>
          </div>
        </section>
      </main>
    </>
  );
}
