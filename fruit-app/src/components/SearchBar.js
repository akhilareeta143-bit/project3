function SearchBar({ keyword, setKeyword, searchFruits, loadFruits }) {
  return (
    <div className="card">
      <div className="search-box">
        <input
          className="search-input"
          placeholder="Search Fruits..."
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
        />
        <button className="btn btn-primary" onClick={searchFruits}>Search</button>
        <button className="btn btn-secondary" onClick={loadFruits}>Reset</button>
      </div>
    </div>
  );
}

export default SearchBar;