# Douse It

A lightweight client-side mod that enhances the visual experience of burning entities. 
**Perfect for immersion and aesthetic focus: removes the intrusive fire overlay and replaces it with neat flame particles and realistic campfire smoke.**

## Features

- 🚫 **No Fire Overlay** - Completely hides the intrusive blocky fire texture overlay on burning entities (including the player).
- 🔥 **Better Particles** - Replaces the overlay with neat flame and smoke particles.
- 🌫️ **Realistic Smoke** - Smoke rises in a column (campfire style) and is distributed naturally across the entity's body.
- ⚙️ **Fully Configurable** - Customize everything:
    - Toggle particles on/off.
    - Adjust spawn chance (density).
    - Configure spread (full body vs centered).
    - Tweak vertical speed.

## Configuration

The configuration file is located at `config/douseit-client.toml`:

- **Particles**:
    - `smoke`: Adjust chance (default 10%), speed, and spread.
    - `flame`: Adjust chance (default 50%), speed, and spread.
- **Fire Texture**:
    - `hideFireTexture`: Set to `false` if you only want the particles but want to keep the vanilla fire overlay (why though?).

## Compatibility

- **Client-Side Only**: You don't need this on the server.
- **Mod Loaders**: Native Forge 1.20.1 support. Easily portable to other loaders.
